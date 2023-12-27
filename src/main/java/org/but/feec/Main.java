package org.but.feec;

import javafx.application.Application;
import org.but.feec.config.DataSourceConfig;


public class Main {
    public static void main(String[] args) {
        DataSourceConfig.initializeDataSource(args);
        App.main(args);
    }
}
