// Copyright (C) 2024 The Android Open Source Project
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
// http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.
package com.googlesource.gerrit.plugins.validation.dfsrefdb.dynamodb;

import com.google.gerrit.server.cache.CacheModule;
import com.google.inject.TypeLiteral;
import java.time.Duration;
import java.util.Optional;

public class ProjectVersionCacheModule extends CacheModule {

  public static final String PROJECT_VERSION_CACHE = "projectVersion";

  @Override
  protected void configure() {
    cache(PROJECT_VERSION_CACHE, String.class, new TypeLiteral<Optional<Integer>>() {})
        .expireAfterWrite(Duration.ofMinutes(5))
        .loader(DynamoDBRefDatabase.ProjectVersionCacheLoader.class);
  }
}
