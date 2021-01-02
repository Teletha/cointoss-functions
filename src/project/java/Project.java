/*
 * Copyright (C) 2021 cointoss-functions Development Team
 *
 * Licensed under the MIT License (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *          https://opensource.org/licenses/MIT
 */
import javax.lang.model.SourceVersion;

/*
 * Copyright (C) 2020 cointoss-functions Development Team
 *
 * Licensed under the MIT License (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *          https://opensource.org/licenses/MIT
 */
public class Project extends bee.api.Project {

    {
        product("com.github.teletha", "cointoss-functions", "1.0");

        require(SourceVersion.RELEASE_11);
        require("com.google.cloud.functions", "functions-framework-api");
        require("com.google.cloud", "google-cloud-nio");
        require("com.github.teletha", "sinobu", "2.1.0");
    }
}