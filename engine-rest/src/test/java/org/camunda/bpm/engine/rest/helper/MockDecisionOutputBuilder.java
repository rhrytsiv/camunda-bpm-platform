/* Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.camunda.bpm.engine.rest.helper;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.camunda.bpm.dmn.engine.DmnDecisionOutput;
import org.camunda.bpm.dmn.engine.DmnDecisionResult;
import org.camunda.bpm.engine.variable.value.TypedValue;

/**
 * @author Philipp Ossler
 */
public class MockDecisionOutputBuilder {

  protected final MockDecisionResultBuilder mockDecisionResultBuilder;

  protected Map<String, Object> outputValues = new HashMap<String, Object>();

  public MockDecisionOutputBuilder(MockDecisionResultBuilder mockDecisionResultBuilder) {
    this.mockDecisionResultBuilder = mockDecisionResultBuilder;
  }

  public MockDecisionOutputBuilder output(String key, Object value) {
    outputValues.put(key, value);
    return this;
  }

  public MockDecisionResultBuilder endDecisionOutput() {
    SimpleDecisionOutput decisionOutput = new SimpleDecisionOutput();
    decisionOutput.putAll(outputValues);

    mockDecisionResultBuilder.addDecisionOutput(decisionOutput);

    return mockDecisionResultBuilder;
  }

  public MockDecisionOutputBuilder decisionOutput() {
    return endDecisionOutput().decisionOutput();
  }

  public DmnDecisionResult build() {
    return endDecisionOutput().build();
  }

  protected class SimpleDecisionOutput extends HashMap<String, Object>implements DmnDecisionOutput {

    private static final long serialVersionUID = 1L;

    @Override
    public <T> T getValue(String name) {
      throw new UnsupportedOperationException();
    }

    @Override
    public <T> T getFirstValue() {
      throw new UnsupportedOperationException();
    }

    @Override
    public <T> T getSingleValue() {
      throw new UnsupportedOperationException();
    }

    @Override
    public <T extends TypedValue> T getValueTyped(String name) {
      throw new UnsupportedOperationException();
    }

    @Override
    public <T extends TypedValue> T getFirstValueTyped() {
      throw new UnsupportedOperationException();
    }

    @Override
    public <T extends TypedValue> T getSingleValueTyped() {
      throw new UnsupportedOperationException();
    }

    @Override
    public boolean containsKey(String key) {
      return super.containsKey(key);
    }

    @Override
    public Collection<TypedValue> valuesTyped() {
      throw new UnsupportedOperationException();
    }

  }

}