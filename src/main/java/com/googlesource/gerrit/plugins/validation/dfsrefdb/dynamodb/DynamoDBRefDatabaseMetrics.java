// Copyright (C) 2023 The Android Open Source Project
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

import com.google.gerrit.extensions.annotations.PluginName;
import com.google.gerrit.metrics.Description;
import com.google.gerrit.metrics.MetricMaker;
import com.google.gerrit.metrics.Timer0;

import com.google.gerrit.metrics.Timer0.Context;
import com.google.inject.Inject;
import com.google.inject.Singleton;

@Singleton
public class DynamoDBRefDatabaseMetrics {

  private final Timer0 lockRefExecutionTime;
  private final Timer0 getPathExecutionTime;
  private Timer0 compareAndPutExecutionTime;

  @Inject
  public DynamoDBRefDatabaseMetrics(@PluginName String pluginName, MetricMaker metricMaker) {
    compareAndPutExecutionTime = metricMaker.newTimer("compare_and_put_latency",
        new Description("Time spent on compareAndPut.")
            .setCumulative()
            .setUnit(Description.Units.MILLISECONDS));
    getPathExecutionTime = metricMaker.newTimer("get_path_latency",
        new Description("Time spent on get path.")
            .setCumulative()
            .setUnit(Description.Units.MILLISECONDS));

    lockRefExecutionTime = metricMaker.newTimer("lock_ref_latency",
        new Description("Time spent on locking ref.")
            .setCumulative()
            .setUnit(Description.Units.MILLISECONDS));
  }

  public Context startCompareAndPutExecutionTime() {
    return compareAndPutExecutionTime.start();
  }

  public Context startGetPathExecutionTime() {
    return getPathExecutionTime.start();
  }

  public Context startLockRefExecutionTime() {
    return lockRefExecutionTime.start();
  }

}
