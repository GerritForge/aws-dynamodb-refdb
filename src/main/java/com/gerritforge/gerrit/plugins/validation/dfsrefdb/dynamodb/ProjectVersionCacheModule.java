// Copyright (C) 2025 GerritForge, Inc.
//
// Licensed under the BSL 1.1 (the "License");
// you may not use this file except in compliance with the License.
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.
package com.gerritforge.gerrit.plugins.validation.dfsrefdb.dynamodb;

import com.google.gerrit.server.cache.CacheModule;
import com.google.inject.TypeLiteral;
import java.time.Duration;
import java.util.Optional;

public class ProjectVersionCacheModule extends CacheModule {

  public static final String PROJECT_VERSION_CACHE = "projectVersion";

  @Override
  protected void configure() {
    cache(PROJECT_VERSION_CACHE, String.class, new TypeLiteral<Optional<Integer>>() {})
        .expireAfterWrite(Duration.ofSeconds(60))
        .maximumWeight(Long.MAX_VALUE)
        .loader(DynamoDBRefDatabase.ProjectVersionCacheLoader.class);
  }
}
