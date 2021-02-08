package com.kmlocek.govstatsproxy.service;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class GovOpenDataResponse {
    List<Data> data;
}

@Getter
@Setter
@NoArgsConstructor
class Data {
    Attributes attributes;
}

@Getter
@Setter
@NoArgsConstructor
class Attributes {
    String col1;
    String col2;
    String col3;
    String col4;
    String col5;
}