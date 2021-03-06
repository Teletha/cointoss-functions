/*
 * Copyright (C) 2021 cointoss-functions Development Team
 *
 * Licensed under the MIT License (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *          https://opensource.org/licenses/MIT
 */
package cointoss;

import java.io.BufferedWriter;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

import com.google.cloud.functions.HttpFunction;
import com.google.cloud.functions.HttpRequest;
import com.google.cloud.functions.HttpResponse;

import kiss.I;
import kiss.JSON;

public class Main implements HttpFunction {

    /**
     * {@inheritDoc}
     */
    @Override
    public void service(HttpRequest request, HttpResponse response) throws Exception {
        BufferedWriter writer = response.getWriter();
        writer.write(bitmex());
    }

    private String bitmex() {
        return I.http("https://www.bitmex.com/api/v1/instrument?columns=openInterest%2CopenValue&filter=%7B%22state%22%3A%22Open%22%7D", JSON.class)
                .waitForTerminate()
                .map(root -> {
                    BitMex mex = new BitMex();

                    for (JSON e : root.find("*")) {
                        double value = e.get(double.class, "openValue");
                        if (value != 0) {
                            BitMexInfo info = new BitMexInfo();
                            info.symbol = e.text("symbol");
                            info.oi = e.get(double.class, "openInterest");
                            info.ov = value;

                            if (!Character.isDigit(info.symbol.charAt(info.symbol.length() - 1))) {
                                mex.infos.add(info);
                            }
                        }
                    }

                    return I.write(mex).replaceAll("\\s", "");
                })
                .to()
                .exact();
    }

    private static class BitMex {

        public List<BitMexInfo> infos = new ArrayList();

        public ZonedDateTime date = ZonedDateTime.now(ZoneId.of("UTC")).truncatedTo(ChronoUnit.MINUTES);
    }

    private static class BitMexInfo {

        public String symbol;

        public double oi;

        public double ov;

        /**
         * {@inheritDoc}
         */
        @Override
        public String toString() {
            return "BitMexInfo [symbol=" + symbol + ", oi=" + oi + ", ov=" + ov + "]";
        }
    }
}