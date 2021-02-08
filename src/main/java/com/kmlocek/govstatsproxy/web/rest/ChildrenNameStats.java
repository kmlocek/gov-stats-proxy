package com.kmlocek.govstatsproxy.web.rest;

import com.kmlocek.govstatsproxy.domain.Gender;
import com.kmlocek.govstatsproxy.domain.Voivodeship;
import com.kmlocek.govstatsproxy.domain.VoivodeshipData;
import com.kmlocek.govstatsproxy.domain.YearlyData;
import com.kmlocek.govstatsproxy.service.GovOpenDataService;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/children-name-stats")
public class ChildrenNameStats {


    GovOpenDataService govOpenDataService;

    public ChildrenNameStats(GovOpenDataService govOpenDataService) {
        this.govOpenDataService = govOpenDataService;
    }

    @GetMapping("/yearly-data")
    public Mono<List<YearlyData>> getYearlyData(@RequestParam String name, @RequestParam Gender gender) {
        return govOpenDataService.getDataFrom2000To2019(name, gender);
    }

    @GetMapping("/voivodeship-data")
    public Mono<List<VoivodeshipData>> getVoivodeshipData(@RequestParam String name,
                                                          @RequestParam Gender gender,
                                                          @RequestParam(required = false) Voivodeship voivodeship) {
        return govOpenDataService.getDataBasedOnVoivodeship(name, gender, voivodeship);
    }


}
