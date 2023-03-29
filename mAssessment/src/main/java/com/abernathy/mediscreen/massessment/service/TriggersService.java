package com.abernathy.mediscreen.massessment.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@PropertySource("classpath:triggers.properties")
public class TriggersService {

    @Value("#{${triggers}}")
    private List<String> triggersList;

    /**
     *
     * @return a String like "(Taille|Poids|Fumeur)(?i)
     */
    protected String buildTriggersRegex() {
        String regex = triggersList.stream().collect(Collectors.joining("|", "(", ")")); // "(s1|s2|s3|...)"
        regex += "(?i)"; // case insensitive
        return regex;
    }


}
