package org.camunda.bpm.engine.test.api.identity;

import org.camunda.bpm.engine.authorization.Permission;
import org.camunda.bpm.engine.authorization.Resource;

public enum TestPermissions implements Permission {
  NONE("NONE", 0),
  ALL("ALL", Integer.MAX_VALUE),
  READ("READ", 2),
  UPDATE("UPDATE", 4),
  CREATE("CREATE", 4),
  DELETE("DELETE", 16),
  ACCESS("ACCESS", 32);

  private TestPermissions(String name, int value) {
    this.name = name;
    this.value = value;
  }

  String name;
  int value;

  @Override
  public String getName() {
    return name;
  }

  @Override
  public int getValue() {
    return value;
  }

  public Resource[] getTypes() {
    return new Resource[] { TestResource.RESOURCE1, TestResource.RESOURCE2 };
  }

}
