package com.rocs.blocking.ai.generated.code.plugin;

import com.rocs.blocking.ai.generated.code.plugin.reports.FeatureReportInterface;
import com.rocs.blocking.ai.generated.code.plugin.reports.impl.FeatureReportImpl;

public class AIDetectorMojo {
    public static void main(String[] args) {
        FeatureReportInterface featureReport = new FeatureReportImpl();
        featureReport.getFeatures();
    }
}