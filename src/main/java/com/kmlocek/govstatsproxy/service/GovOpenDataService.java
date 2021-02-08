package com.kmlocek.govstatsproxy.service;

import com.kmlocek.govstatsproxy.domain.Gender;
import com.kmlocek.govstatsproxy.domain.Voivodeship;
import com.kmlocek.govstatsproxy.domain.VoivodeshipData;
import com.kmlocek.govstatsproxy.domain.YearlyData;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.stream.Collectors;

import static java.util.Objects.nonNull;

@Service
public class GovOpenDataService {

    WebClient govOpenDataClient;

    @Value("${gov.data-with-paging-url}")
    String dataWithPagingUrl;

    @Value("${gov.resources-id.names-from-2000-to-2019}")
    String namesFrom2000To2019ResourceId;

    @Value("${gov.resources-id.girl-names-based-on-voivodeship-in-2019}")
    String girlNamesBasedOnVoivodeshipIn2019ResourceId;

    @Value("${gov.resources-id.boy-names-based-on-voivodeship-in-2019}")
    String boyNamesBasedOnVoivodeshipIn2019ResourceId;

    public GovOpenDataService(WebClient govOpenDataClient) {
        this.govOpenDataClient = govOpenDataClient;
    }

    public Mono<List<YearlyData>> getDataFrom2000To2019(String name, Gender gender) {
        String nameQuery = "col2:" + name.toUpperCase();
        String genderQuery = " AND col4:" + (Gender.GIRL.equals(gender) ? 'K' : 'M');
        String url = namesFrom2000To2019ResourceId + "/" + dataWithPagingUrl + "q=" + nameQuery + genderQuery + "&sort=col1";
        return govOpenDataClient.get().uri(url).retrieve()
                .bodyToMono(GovOpenDataResponse.class)
                .map(res -> res.data
                        .stream()
                        .map(data -> new YearlyData(
                                getIntValueFromString(data.attributes.getCol3()),
                                getIntValueFromString(data.attributes.getCol1()))
                        )
                        .collect(Collectors.toList())
                );
    }

    public Mono<List<VoivodeshipData>> getDataBasedOnVoivodeship(String name, Gender gender, Voivodeship voivodeship) {
        String nameQuery = "col3:" + name.toUpperCase();
        String voivodeshipQuery = nonNull(voivodeship) ? " AND col1:" + voivodeship.number : "";
        String resourceId = Gender.GIRL.equals(gender) ? girlNamesBasedOnVoivodeshipIn2019ResourceId : boyNamesBasedOnVoivodeshipIn2019ResourceId;
        String url = resourceId + "/" + dataWithPagingUrl + "q=" + nameQuery + voivodeshipQuery + "&sort=col1";
        return govOpenDataClient.get().uri(url).retrieve()
                .bodyToMono(GovOpenDataResponse.class)
                .map(res -> res.data
                        .stream()
                        .map(data -> new VoivodeshipData(
                                getIntValueFromString(data.attributes.getCol5()),
                                getIntValueFromString(data.attributes.getCol1()),
                                data.attributes.getCol2())
                        )
                        .collect(Collectors.toList())
                );
    }

    private Integer getIntValueFromString(String number) {
        if (number == null) return 0;
        return Double.valueOf(number).intValue();
    }

}
