package com.weber.cs3230.generators;

import com.weber.cs3230.AlexaDAO;
import com.weber.cs3230.NoAvailableAnswerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class DBAnswerGenerator {

    private final AlexaDAO alexaDAO;
    @Autowired
    public DBAnswerGenerator(AlexaDAO alexaDAO)    {
        this.alexaDAO = alexaDAO;
    }

    private List<String> list;
    private String lastStr;
    private boolean first = false;
    public String getAnswerText(String intentString) throws NoAvailableAnswerException {
        list = alexaDAO.getAnswersForIntent(intentString);
        if (list.size() == 0) {
            throw new NoAvailableAnswerException();
        }
        if (list.size() == 1)    {
            return list.get(0);
        }
        if (first) {
            while (lastStr.equals(list.get(0))) {
                Collections.shuffle(list);
            }
        }
        first = true;
        lastStr = list.get(0);
        return list.get(0);
    }
}
