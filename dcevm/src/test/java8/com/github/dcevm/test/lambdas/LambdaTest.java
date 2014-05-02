/*
 * Copyright (c) 2010, Oracle and/or its affiliates. All rights reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This code is free software; you can redistribute it and/or modify it
 * under the terms of the GNU General Public License version 2 only, as
 * published by the Free Software Foundation.
 *
 * This code is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public License
 * version 2 for more details (a copy is included in the LICENSE file that
 * accompanied this code).
 *
 * You should have received a copy of the GNU General Public License version
 * 2 along with this work; if not, write to the Free Software Foundation,
 * Inc., 51 Franklin St, Fifth Floor, Boston, MA 02110-1301 USA.
 *
 * Please contact Oracle, 500 Oracle Parkway, Redwood Shores, CA 94065 USA
 * or visit www.oracle.com if you need additional information or have any
 * questions.
 *
 */

package com.github.dcevm.test.lambdas;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.util.concurrent.Callable;

import static com.github.dcevm.test.util.HotSwapTestHelper.__toVersion__;
import static com.github.dcevm.test.util.HotSwapTestHelper.__version__;
import static org.junit.Assert.assertEquals;

/**
 * Tests for lambda expressions
 *
 * @author Ivan Dubrov
 */
public class LambdaTest {

  @Before
  @After
  public void setUp() throws Exception {
    __toVersion__(0);
  }

  // Version 0
  public static class A {
    public Callable<Integer> createLambda() {
      return () -> 10;
    }

    public Callable<Integer> createLambda2() {
      return () -> 20;
    }
  }

  // Version 1
  public static class A___1 {
    public Callable<Integer> createLambda2() {
      return () -> 40;
    }

    public Callable<Integer> createLambda() {
      return () -> 30;
    }
  }

  @Test
  @Ignore
  public void testMethodLambda() throws Exception {
    A a = new A();
    Callable<Integer> lambda = a.createLambda();
    Callable<Integer> lambda2 = a.createLambda2();

    assertEquals(10, (int) lambda.call());
    assertEquals(20, (int) lambda2.call());

    __toVersion__(1);

    assertEquals(30, (int) lambda.call());
    assertEquals(40, (int) lambda2.call());
  }
}