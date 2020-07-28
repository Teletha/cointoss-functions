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

        require("com.google.cloud.functions", "functions-framework-api");
    }
}