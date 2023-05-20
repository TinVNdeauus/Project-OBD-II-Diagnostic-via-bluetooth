package com.example.bluetooth_obd_ii_diagnostic.bluetoothterminal.PIDsEnums;

import java.util.HashMap;
import java.util.Map;

public enum FirstModeRequestEnums {
    PIDs_SUPPORTED_01_20("00"),
    MONITOR_STATUS_SINCE_DTCs_CLEARED("01"),
    FUEL_SYSTEM_STATUS("03"),
    CALCULATED_ENGINE_LOAD("04"),
    ENGINE_COOLANT_TEMPERATURE("05"),
    SHORT_TERM_FUEL_TRIM_BANK_1("06"),
    LONG_TERM_FUEL_TRIM_BANK_1("07"),
    SHORT_TERM_FUEL_TRIM_BANK_2("08"),
    LONG_TERM_FUEL_TRIM_BANK_2("09"),
    FUEL_PRESSURE("0A"),
    INTAKE_MANIFOLD_ABSOLUTE_PRESSURE("0B"),
    ENGINE_RPM("0C"),
    VEHICLE_SPEED("0D"),
    TIMING_ADVANCE("0E"),
    INTAKE_AIR_TEMPERATURE("0F"),
    MAF_AIR_FLOW_RATE("10"),
    THROTTLE_POSITION("11"),
    COMMANDED_SECONDARY_AIR_STATUS("12"),
    OXYGEN_SENSORS_PRESENT_BANKS_1_2("13"),
    OXYGEN_SENSOR_1_SHORT_TERM_FUEL_TRIM("14"),
    OXYGEN_SENSOR_2_SHORT_TERM_FUEL_TRIM("15"),
    OXYGEN_SENSOR_3_SHORT_TERM_FUEL_TRIM("16"),
    OXYGEN_SENSOR_4_SHORT_TERM_FUEL_TRIM("17"),
    OXYGEN_SENSOR_5_SHORT_TERM_FUEL_TRIM("18"),
    OXYGEN_SENSOR_6_SHORT_TERM_FUEL_TRIM("19"),
    OXYGEN_SENSOR_7_SHORT_TERM_FUEL_TRIM("1A"),
    OXYGEN_SENSOR_8_SHORT_TERM_FUEL_TRIM("1B"),
    OBD_STANDARDS_THIS_VEHICLE_CONFORMS_TO("1C"),
    OXYGEN_SENSORS_PRESENT_BANKS_1_4("1D"),
    AUXILIARY_INPUT_STATUS("1E"),
    RUNTIME_SINCE_ENGINE_START("1F"),
    PIDs_SUPPORTED_21_40("20"),
    DISTANCE_TRAVELLED_WITH_MIL_ON("21"),
    FUEL_RAIL_PRESSURE("22"),
    FUEL_RAIL_PRESSURE_DIESEL_OR_GASOLINE_DIRECT_INJECTION("23"),
    OXYGEN_SENSOR_1_FUEL_AIR_RATIO_VOLTAGE("24"),
    OXYGEN_SENSOR_2_FUEL_AIR_RATIO_VOLTAGE("25"),
    OXYGEN_SENSOR_3_FUEL_AIR_RATIO_VOLTAGE("26"),
    OXYGEN_SENSOR_4_FUEL_AIR_RATIO_VOLTAGE("27"),
    OXYGEN_SENSOR_5_FUEL_AIR_RATIO_VOLTAGE("28"),
    OXYGEN_SENSOR_6_FUEL_AIR_RATIO_VOLTAGE("29"),
    OXYGEN_SENSOR_7_FUEL_AIR_RATIO_VOLTAGE("2A"),
    OXYGEN_SENSOR_8_FUEL_AIR_RATIO_VOLTAGE("2B"),
    COMMANDED_EGR("2C"),
    EGR_ERROR("2D"),
    COMMANDED_EVAPORATIVE_PURGE("2E"),
    FUEL_TANK_LEVEL_INPUT("2F"),
    NUMBER_OF_WARM_UPS_SINCE_CODES_CLEARED("30"),
    DISTANCE_TRAVELED_SINCE_CODES_CLEARED("31"),
    EVAPORATIVE_SYSTEM_VAPOUR_PRESSURE_("32"),
    ABSOLUTE_BAROMETRIC_PRESSURE("33"),
    OXYGEN_SENSOR_1_FUEL_AIR_RATIO_CURRENT("34"),
    OXYGEN_SENSOR_2_FUEL_AIR_RATIO_CURRENT("35"),
    OXYGEN_SENSOR_3_FUEL_AIR_RATIO_CURRENT("36"),
    OXYGEN_SENSOR_4_FUEL_AIR_RATIO_CURRENT("37"),
    OXYGEN_SENSOR_5_FUEL_AIR_RATIO_CURRENT("38"),
    OXYGEN_SENSOR_6_FUEL_AIR_RATIO_CURRENT("39"),
    OXYGEN_SENSOR_7_FUEL_AIR_RATIO_CURRENT("3A"),
    OXYGEN_SENSOR_8_FUEL_AIR_RATIO_CURRENT("3B"),
    CATALYST_TEMPERATURE_BANK_1_SENSOR_1("3C"),
    CATALYST_TEMPERATURE_BANK_2_SENSOR_1("3D"),
    CATALYST_TEMPERATURE_BANK_1_SENSOR_2("3E"),
    CATALYST_TEMPERATURE_BANK_2_SENSOR_2("3F"),
    PIDs_SUPPORTED_41_60("40"),
    MONITOR_STATUS_THIS_DRIVE_CYCLE("41"),
    CONTROL_MODULE_VOLTAGE("42"),
    ABSOLUTE_LOAD_VALUE("43"),
    COMMAND_EQUIVALENCE_RATIO("44"),
    RELATIVE_THROTTLE_POSITION("45"),
    AMBIENT_AIR_TEMPERATURE("46"),
    ABSOLUTE_THROTTLE_POSITION_B("47"),
    ABSOLUTE_THROTTLE_POSITION_C("48"),
    ABSOLUTE_THROTTLE_POSITION_D("49"),
    ABSOLUTE_THROTTLE_POSITION_E("4A"),
    ABSOLUTE_THROTTLE_POSITION_F("4B"),
    COMMANDED_THROTTLE_ACTUATOR("4C"),
    TIME_RUN_WITH_MIL_ON("4D"),
    TIME_SINCE_TROUBLE_CODES_CLEARED("4E"),
    MAXIMUM_VALUE_FOR_EQUIVALENCE_RATIO_OXYGEN_SENSOR_VOLTAGE_OXYGEN_SENSOR_CURRENT_AND_INTAKE_MANIFOLD_ABSOLUTE_PRESSURE("4F"),
    MAXIMUM_VALUE_FOR_AIR_FLOW_RATE_FROM_MASS_AIR_FLOW_SENSOR("50"),
    FUEL_TYPE("51"),
    ETHANOL_FUEL_PERCENTAGE("52"),
    ABSOLUTE_EVAPORATIVE_SYSTEM_VAPOUR_PRESSURE("53"),
    EVAPORATIVE_SYSTEM_VAPOUR_PRESSURE_2("54"),
    SHORT_TERM_SECONDARY_OXYGEN_SENSOR_FUEL_TRIM_BANK1_AND_BANK_3("55"),
    LONG_TERM_SECONDARY_OXYGEN_SENSOR_FUEL_TRIM_BANK1_AND_BANK_3("56"),
    SHORT_TERM_SECONDARY_OXYGEN_SENSOR_FUEL_TRIM_BANK2_AND_BANK_4("57"),
    LONG_TERM_SECONDARY_OXYGEN_SENSOR_FUEL_TRIM_BANK2_AND_BANK_4("58"),
    FUEL_RAIL_PRESSURE_ABSOULTE("59"),
    RELATIVE_ACCELERATOR_PEDAL_POSITION("5A"),
    HYBRID_BATTERY_PACK_REMAINING_LIFE("5B"),
    ENGINE_OIL_TEMPERATURE_("5C"),
    FUEL_INJECTION_TIMING_("5D"),
    ENGINE_FUEL_RATE_("5E")
    ;

    private static final Map methodMap = new HashMap();

    static {
        for (FirstModeRequestEnums enums : FirstModeRequestEnums.values())
            methodMap.put(enums.getValue(), enums);
    }

    private final String value;

    FirstModeRequestEnums(String value) {this.value = value;}

    public final String getValue() {
        return value;
    }

    public static String getEnum( String value){
        return String.valueOf(methodMap.get(value));
    }

}
