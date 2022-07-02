package com.weber.cs3230.generators;

import com.weber.cs3230.AlexaDAO;
import com.weber.cs3230.NoAvailableAnswerException;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DBAnswerGenerator {

    private final AlexaDAO alexaDAO;
    @Autowired
    public DBAnswerGenerator(AlexaDAO alexaDAO)    {
        this.alexaDAO = alexaDAO;
    }

    public String getAnswerText(String intentString) throws NoAvailableAnswerException {
        List<String> list = alexaDAO.getAnswersForIntent(intentString);
        if (list.size() == 0) {
            throw new NoAvailableAnswerException();
        }
        if (list.size() == 1)    {
            return list.get(0);
        }
        String lastStr = list.get(0);
        while (lastStr.equals(list.get(0))) {
            Collections.shuffle(list);
        }

        return list.get(0);
    }
}
