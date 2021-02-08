package com.kmlocek.govstatsproxy.domain;

import java.util.Arrays;

public enum Voivodeship {
    LOWER_SILESIA("DOLNOŚLĄSKIE", 2),
    KUYAVIA_POMERANIA("KUJAWSKO-POMORSKIE", 4),
    LUBLIN("LUBELSKIE", 6),
    LUBUSZ("LUBUSKIE", 8),
    LODZKIE("ŁÓDZKIE", 10),
    LESSER_POLAND("MAŁOPOLSKIE", 12),
    MASOVIA("MAZOWIECKIE", 14),
    OPOLE("OPOLSKIE", 16),
    SUBCARPATIAN("PODKARPACKIE", 18),
    POMERANIA("POMORSKIE", 22),
    SILESIAN("ŚLĄSKIE", 24),
    SWIETOKRZYSKIE("ŚWIĘTOKRZYSKIE", 26),
    WARMIA_MASURIA("WARMIŃSKO_MAZURSKIE", 28),
    GREATER_POLAND("WIELKOPOLSKIE", 30),
    WEST_POMERANIA("ZACHODNIOPOMORSKIE", 32);

    public final Integer number;
    public final String polishName;

    Voivodeship(String polishName, Integer number) {
        this.number = number;
        this.polishName = polishName;
    }

    public Voivodeship getByPolishName(String polishName) {
        return Arrays.stream(Voivodeship.values())
                .filter(voivodeship -> voivodeship.polishName.equals(polishName))
                .findFirst()
                .orElse(null);
    }

    public Voivodeship getByNumber(Integer number) {
        return Arrays.stream(Voivodeship.values())
                .filter(voivodeship -> voivodeship.number.equals(number))
                .findFirst()
                .orElse(null);
    }
}
