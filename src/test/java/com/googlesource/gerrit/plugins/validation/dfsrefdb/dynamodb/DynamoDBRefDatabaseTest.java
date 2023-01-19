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
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.any;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBLockClient;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.GetItemResult;
import com.google.gerrit.entities.Project;
import com.google.gerrit.metrics.Timer0.Context;
import java.util.Collections;
import java.util.Map;
import org.eclipse.jgit.lib.ObjectId;
import org.eclipse.jgit.lib.Ref;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class DynamoDBRefDatabaseTest {
  @Mock
  private AmazonDynamoDBLockClient lockClient;
  @Mock
  private AmazonDynamoDB dynamoDBClient;
  @Mock
  private Configuration configuration;
  @Mock
  private DynamoDBRefDatabaseMetrics metrics;
  @Mock
  private Context context;
  @Mock
  private Ref ref;
  @Mock
  private GetItemResult pathResult;
  private Map<String, AttributeValue> item = Collections.emptyMap();

  private DynamoDBRefDatabase objectUnderTest;
  private String refName = "refs/heads/master";
  private Project.NameKey projectName = Project.nameKey("test_project");

  @Before
  public void setup() {
    when(metrics.startCompareAndPutExecutionTime()).thenReturn(context);
    when(metrics.startLockRefExecutionTime()).thenReturn(context);
    when(metrics.startGetPathExecutionTime()).thenReturn(context);
    when(ref.getName()).thenReturn(refName);
    when(pathResult.getItem()).thenReturn(item);
    when(dynamoDBClient.getItem(any(), any(), any())).thenReturn(pathResult);

    objectUnderTest = new DynamoDBRefDatabase(lockClient, dynamoDBClient, configuration, metrics);
  }

  @Test
  public void shouldUpdateCompareAndPutExecutionTimeMetricWhenCompareAndPut() {
    objectUnderTest.compareAndPut(projectName, refName, ObjectId.zeroId(),ObjectId.zeroId());
    verify(metrics, times(1)).startCompareAndPutExecutionTime();
    verify(context, times(1)).stop();
  }

  @Test
  public void shouldUpdateLockRefExecutionTimeMetricWhenLockRef() {
    objectUnderTest.lockRef(projectName, refName);
    verify(metrics, times(1)).startLockRefExecutionTime();
    verify(context, times(1)).stop();
  }

  @Test
  public void shouldGetPathExecutionTimeMetricWhenIsUpToDate() {
    objectUnderTest.isUpToDate(projectName, ref);
    verifyGetPath();
  }

  @Test
  public void shouldGetPathExecutionTimeMetricWhenExists() {
    objectUnderTest.exists(projectName, refName);
    verifyGetPath();
  }

  @Test
  public void shouldGetPathExecutionTimeMetricWhenGet() {
    objectUnderTest.get(projectName, refName, String.class);
    verifyGetPath();
  }

  private void verifyGetPath() {
    verify(metrics, times(1)).startGetPathExecutionTime();
    verify(context, times(1)).stop();
  }

}
