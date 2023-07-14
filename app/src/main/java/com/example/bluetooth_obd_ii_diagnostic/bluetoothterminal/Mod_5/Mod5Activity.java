package com.example.bluetooth_obd_ii_diagnostic.bluetoothterminal.Mod_5;


import android.Manifest;
import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;

import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.example.bluetooth_obd_ii_diagnostic.R;
import com.example.bluetooth_obd_ii_diagnostic.bluetoothterminal.PIDsEnums.FifthModeRequestEnums;


import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Set;
import java.util.UUID;

public class Mod5Activity extends AppCompatActivity {

    private final String DEVICE_ADDRESS = "00:00:00:00:11:11";
    private final UUID PORT_UUID = UUID.fromString("00001101-0000-1000-8000-00805f9b34fb");//Serial Port Service ID
    private final String MOD_5_PREFIX = "45";
    private BluetoothDevice device;
    private BluetoothSocket socket;
    private OutputStream outputStream;
    private InputStream inputStream;
    private static final int REQUEST_BLUETOOTH_PERMISSION = 1;
    Button startButton, sendButton, clearButton, stopButton;
    Spinner MOD_5_LIST;
    TextView textView;
    boolean deviceConnected = false;
    Thread thread;
    byte buffer[];
    int bufferPosition;
    boolean stopThread;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mod_5_activity);
        startButton = (Button) findViewById(R.id.buttonStart);
        sendButton = (Button) findViewById(R.id.buttonSend);
        clearButton = (Button) findViewById(R.id.buttonClear);
        stopButton = (Button) findViewById(R.id.buttonStop);
        textView = (TextView) findViewById(R.id.textView);
        MOD_5_LIST = (Spinner) findViewById(R.id.mod5list);

        ArrayAdapter<FifthModeRequestEnums> mod5adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, FifthModeRequestEnums.values());
        mod5adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        MOD_5_LIST.setAdapter(mod5adapter);

        setupUserInterface(false);
    }
    ActivityResultLauncher<Intent> requestBluetoothLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if (result.getResultCode() == Activity.RESULT_OK) {
                    // Bluetooth was enabled by the user
                } else {
                    // Bluetooth was not enabled by the user
                }
            }
    );
    public void setupUserInterface(boolean bool) {
        startButton.setEnabled(!bool);
        sendButton.setEnabled(bool);
        stopButton.setEnabled(bool);
        textView.setEnabled(bool);
    }

    public boolean initializeBluetoothAdapter() {
        boolean found = false;
        BluetoothAdapter bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        if (bluetoothAdapter == null) {
            Toast.makeText(getApplicationContext(), "Bluetooth không được hỗ trợ", Toast.LENGTH_SHORT).show();
        }
        if (!bluetoothAdapter.isEnabled()) {
            Intent enableAdapter = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            requestBluetoothLauncher.launch(enableAdapter);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.BLUETOOTH) == PackageManager.PERMISSION_GRANTED) {
            // Quyền đã được cấp, tiếp tục truy cập các thiết bị đã ghép nối
            Set<BluetoothDevice> bondedDevices = bluetoothAdapter.getBondedDevices();
            if (bondedDevices.isEmpty()) {
                Toast.makeText(getApplicationContext(), "Thiết bị chưa được ghép nối", Toast.LENGTH_SHORT).show();
            } else {
                for (BluetoothDevice deviceIterator : bondedDevices) {
                    if (deviceIterator.getAddress().equals(DEVICE_ADDRESS)) {
                        device = deviceIterator;
                        found = true;
                        break;
                    }
                }
            }
        } else {
            // Quyền chưa được cấp, yêu cầu nó
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.BLUETOOTH}, REQUEST_BLUETOOTH_PERMISSION);
        }

        return found;
    }

    public boolean initializeBluetoothConnection() {
        boolean connected = true;
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.BLUETOOTH) == PackageManager.PERMISSION_GRANTED) {
            // Quyền đã được cấp, tiếp tục thiết lập kết nối Bluetooth
            try {
                socket = device.createRfcommSocketToServiceRecord(PORT_UUID);
                socket.connect();
            } catch (IOException e) {
                e.printStackTrace();
                connected = false;
            }
            if (connected) {
                try {
                    outputStream = socket.getOutputStream();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                try {
                    inputStream = socket.getInputStream();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } else {
            // Quyền chưa được cấp, yêu cầu nó
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.BLUETOOTH}, REQUEST_BLUETOOTH_PERMISSION);
            connected = false;
        }
        return connected;
    }



    public void onClickStart(View view) {

        if (initializeBluetoothAdapter()) {
            if (initializeBluetoothConnection()) {
                setupUserInterface(true);
                deviceConnected = true;
                dataListening();
                textView.append("\nConnection Opened!\n");
            }
        }
    }

    void dataListening() {
        final Handler handler = new Handler();
        stopThread = false;
        buffer = new byte[1024];
        Thread thread = new Thread(new Runnable() {
            public void run() {
                while (!Thread.currentThread().isInterrupted() && !stopThread) {
                    try {
                        int byteCount = inputStream.available();
                        if (byteCount > 0) {
                            byte[] rawBytes = new byte[byteCount];
                            inputStream.read(rawBytes);
                            final String rawResponse = new String(rawBytes, "UTF-8");

                            if (rawResponse.startsWith(MOD_5_PREFIX)) {
                                final String response = MOD5ResponseCalculator.MOD5ResponseCalculator(rawResponse);
                                handler.post(new Runnable() {
                                    public void run() { textView.append(response); }});
                            } else {
                                handler.post(new Runnable() {
                                    public void run() { textView.append(rawResponse); }});
                            }
                        }
                    } catch (IOException ex) {
                        stopThread = true;
                    }
                }
            }
        });

        thread.start();
    }

    /**    REQUEST FORMAT
     *       ____   ____
     *      | 05 | |PID |
     *      |____| |____|
     */
    public void onClickSend(View view) {
        String PID = MOD_5_LIST.getSelectedItem().toString();
        String requestPID = "05" + FifthModeRequestEnums.valueOf(PID).getValue();
        requestPID.concat("\n");
        try {
            outputStream.write(requestPID.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
        textView.append("\nRequest:" + PID + "\n");

    }

    public void onClickStop(View view) throws IOException {
        stopThread = true;
        outputStream.close();
        inputStream.close();
        socket.close();
        setupUserInterface(false);
        deviceConnected = false;
        textView.append("\nConnection Closed!\n");
    }

    public void onClickClear(View view) {
        textView.setText("");
    }
}
