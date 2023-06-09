package io.debezium.perf.load;

import com.fasterxml.jackson.core.JsonProcessingException;
import io.debezium.perf.load.data.builder.PersonDataBuilder;
import io.debezium.perf.load.data.builder.RequestBuilder;
import io.debezium.perf.load.graph.GraphVisualisation;
import io.debezium.perf.load.scenarios.ScenarioRequestExecutor;
import io.debezium.perf.load.scenarios.builder.PeakScenarioBuilder;

import java.net.URISyntaxException;

public class Generator {

    public static void main(String[] args) throws URISyntaxException, JsonProcessingException {
        int rate = 5000;
        RequestBuilder<PersonDataBuilder, PeakScenarioBuilder> requestBuilder
                = new RequestBuilder<>(new PersonDataBuilder(), new PeakScenarioBuilder(2000, 15, 5000, 40));

        ScenarioRequestExecutor sc = requestBuilder
                .setMaxRows(500)
                .setRequestCount(55000)
                .setEndpoint("http://dmt-route-debezium-apps.apps.worker-01.strimzi.app-services-dev.net/Main/CreateTableAndUpsert")
                .buildScenario(rate);
        GraphVisualisation.drawGraph(sc);
        int x = 5;
    }
}
