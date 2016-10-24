package com.mtsmda.db;

import com.mtsmda.generator.GenerateRandom;
import com.mtsmda.generator.GenerateRandomDate;
import com.mtsmda.helper.ListHelper;
import com.mtsmda.helper.LocalDateTimeHelper;

import java.sql.Types;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by dminzat on 10/23/2016.
 */
public class GenerateDefaultDataForDB {

    public static void insert() {
        StringBuilder builderResult = new StringBuilder();
        String query = "INSERT INTO T_PAYMENT_ADMIN_TXN(PAYMENT_ADMIN_TXN_ID, PAYMENT_ADMIN_BUSINESS_KEY, PARTITION_DATE, SWITCH_RECEIVED_TMSTMP, INSERT_TMSTMP, BATCH_ID, CHECKSUM, MESSAGE_TYPE_CODE, SENDER_PARTICIPANT_IDENT, RECEIVER_PARTICIPANT_IDENT)" +
                "VALUES(:PAYMENT_ADMIN_TXN_ID, ':PAYMENT_ADMIN_BUSINESS_KEY', TO_DATE(':PARTITION_DATE', 'YYYY-MM-DD HH24:MI:SS'), TO_DATE(':SWITCH_RECEIVED_TMSTMP', 'YYYY-MM-DD HH24:MI:SS'), TO_DATE(':INSERT_TMSTMP', 'YYYY-MM-DD HH24:MI:SS'), :BATCH_ID, :CHECKSUM, ':MESSAGE_TYPE', ':SENDER_PARTICIPANT_IDENT', ':RECEIVER_PARTICIPANT_IDENT');";

        List<String> messageTypes = ListHelper.getListWithData("pain.013.001.05", "pain.014.001.05", "pain.026.001.05", "pain.028.001.06",
                "pain.029.001.06", "pain.056.001.01", "remt.001.001.02", "camt.035.001.03");

        List<String> participants = ListHelper.getListWithData("000000002A2", "000000001A1", "000000002A1");

        int count = messageTypes.size() - 1;
        int countParticipants = participants.size() - 1;
        for (int i = 0; i < 250; i++) {
            String inQuery = query;
            inQuery = inQuery.replace(":PAYMENT_ADMIN_TXN_ID", ((i + 1) * 9) + "");
            inQuery = inQuery.replace(":PAYMENT_ADMIN_BUSINESS_KEY", "Participant_" + i);
            inQuery = inQuery.replace(":PARTITION_DATE", LocalDateTimeHelper.convertLocalDateTimeToString(GenerateRandomDate.getRandomLocalDateTime(2015, 2016), LocalDateTimeHelper.ORACLE_DATE_TIME_FORMAT));
            inQuery = inQuery.replace(":SWITCH_RECEIVED_TMSTMP", LocalDateTimeHelper.convertLocalDateTimeToString(GenerateRandomDate.getRandomLocalDateTime(2015, 2016), LocalDateTimeHelper.ORACLE_DATE_TIME_FORMAT));
            inQuery = inQuery.replace(":INSERT_TMSTMP", LocalDateTimeHelper.convertLocalDateTimeToString(GenerateRandomDate.getRandomLocalDateTime(2015, 2016), LocalDateTimeHelper.ORACLE_DATE_TIME_FORMAT));
            inQuery = inQuery.replace(":BATCH_ID", GenerateRandom.generateRandomNumberInRange(10, 100) + "");
            inQuery = inQuery.replace(":CHECKSUM", GenerateRandom.generateRandomNumberInRange(10, 1_000) + "");
            inQuery = inQuery.replace(":MESSAGE_TYPE", messageTypes.get(GenerateRandom.generateRandomNumberInRange(0, count)));
            inQuery = inQuery.replace(":SENDER_PARTICIPANT_IDENT", participants.get(GenerateRandom.generateRandomNumberInRange(0, countParticipants)));
            inQuery = inQuery.replace(":RECEIVER_PARTICIPANT_IDENT", participants.get(GenerateRandom.generateRandomNumberInRange(0, countParticipants)));

            builderResult.append(inQuery).append("\n");
        }

        System.out.println(builderResult);
    }

    public static void main(String[] args) {
//        insert();
        Map<String, Integer> fieldNameAndType = new LinkedHashMap<>();
        fieldNameAndType.put("PAYMENT_ADMIN_TXN_ID", Types.INTEGER);
        fieldNameAndType.put("PAYMENT_ADMIN_BUSINESS_KEY", Types.VARCHAR);
        fieldNameAndType.put("PARTITION_DATE", Types.DATE);
        fieldNameAndType.put("SWITCH_RECEIVED_TMSTMP", Types.TIMESTAMP);
        System.out.println(createQuery("T_PAYMENT_ADMIN_TXN", fieldNameAndType));
    }

    private StringBuilder insert(String query, Map<String, Integer> mapParamNameWithType, int countGenerate) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < countGenerate; i++) {
            String innerQuery = query;
            mapParamNameWithType.forEach((key, value) -> {
                switch (value) {
                    case Types.VARCHAR: {
//                        innerQuery = innerQuery.replace(key, )
                    }
                    break;
                }
            });
            result.append("\n");
        }
        return result;
    }

    public static StringBuilder createQuery(String tableName, Map<String, Integer> fieldNameAndTYpe) {
        StringBuilder result = new StringBuilder("INSERT INTO ").append(tableName).append(" (");
        Set<String> strings = fieldNameAndTYpe.keySet();
        System.out.println(strings.toString());
        result.append(")VALUES(");
        return result.append(");");
    }

}