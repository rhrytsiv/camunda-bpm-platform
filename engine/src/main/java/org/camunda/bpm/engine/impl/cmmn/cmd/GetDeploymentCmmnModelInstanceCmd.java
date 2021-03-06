/*
 * Copyright © 2013-2018 camunda services GmbH and various authors (info@camunda.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.camunda.bpm.engine.impl.cmmn.cmd;

import static org.camunda.bpm.engine.impl.util.EnsureUtil.ensureNotNull;

import org.camunda.bpm.engine.exception.cmmn.CmmnModelInstanceNotFoundException;
import org.camunda.bpm.engine.impl.cfg.CommandChecker;
import org.camunda.bpm.engine.impl.cfg.ProcessEngineConfigurationImpl;
import org.camunda.bpm.engine.impl.cmmn.entity.repository.CaseDefinitionEntity;
import org.camunda.bpm.engine.impl.context.Context;
import org.camunda.bpm.engine.impl.interceptor.Command;
import org.camunda.bpm.engine.impl.interceptor.CommandContext;
import org.camunda.bpm.engine.impl.persistence.deploy.cache.DeploymentCache;
import org.camunda.bpm.model.cmmn.CmmnModelInstance;

/**
 * @author Daniel Meyer
 * @author Roman Smirnov
 *
 */
public class GetDeploymentCmmnModelInstanceCmd implements Command<CmmnModelInstance> {

  protected String caseDefinitionId;

  public GetDeploymentCmmnModelInstanceCmd(String caseDefinitionId) {
    this.caseDefinitionId = caseDefinitionId;
  }

  public CmmnModelInstance execute(CommandContext commandContext) {
    ensureNotNull("caseDefinitionId", caseDefinitionId);

    ProcessEngineConfigurationImpl configuration = Context.getProcessEngineConfiguration();
    final DeploymentCache deploymentCache = configuration.getDeploymentCache();

    CaseDefinitionEntity caseDefinition = deploymentCache.findDeployedCaseDefinitionById(caseDefinitionId);

    for(CommandChecker checker : commandContext.getProcessEngineConfiguration().getCommandCheckers()) {
      checker.checkReadCaseDefinition(caseDefinition);
    }

    CmmnModelInstance modelInstance = Context
        .getProcessEngineConfiguration()
        .getDeploymentCache()
        .findCmmnModelInstanceForCaseDefinition(caseDefinitionId);

    ensureNotNull(CmmnModelInstanceNotFoundException.class, "No CMMN model instance found for case definition id " + caseDefinitionId, "modelInstance", modelInstance);
    return modelInstance;
  }

}
