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
package org.camunda.bpm.engine.history;

import org.camunda.bpm.engine.exception.NotValidException;
import org.camunda.bpm.engine.query.Report;

import java.util.Date;

/**
 * <p>Defines a report query for {@link HistoricTaskInstance}s.</p>
 *
 * @author Stefan Hentschel
 *
 */
public interface HistoricTaskInstanceDurationReport extends Report {

  /**
   * Only takes historic process instances into account that were completed after the given date.
   *
   * @throws NotValidException if the given completed after date is null
   *
   */
  HistoricTaskInstanceDurationReport completedAfter(Date completedAfter);

  /**
   * Only takes historic process instances into account that were completed before the given date.
   *
   * @throws NotValidException if the given completed before date is null
   */
  HistoricTaskInstanceDurationReport completedBefore(Date completedBefore);


}