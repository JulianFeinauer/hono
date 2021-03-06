/*******************************************************************************
 * Copyright (c) 2019, 2020 Contributors to the Eclipse Foundation
 *
 * See the NOTICE file(s) distributed with this work for additional
 * information regarding copyright ownership.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/

package org.eclipse.hono.service.management.device;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import io.vertx.core.json.Json;
import io.vertx.core.json.JsonObject;

/**
 * Verifies {@link Device}.
 */
public class DeviceTest {

    /**
     * Decode device with absent "enabled" flag.
     */
    @Test
    public void testDecodeDefault() {
        final var device = Json.decodeValue("{}", Device.class);
        assertThat(device).isNotNull();
        assertThat(device.isEnabled());
    }


    /**
     * Decode device with "enabled=false".
     */
    @Test
    public void testDecodeDisabled() {
        final var device = Json.decodeValue("{\"enabled\": false}", Device.class);
        assertThat(device).isNotNull();
        assertThat(device.isEnabled()).isFalse();
    }

    /**
     * Decode device with "enabled=true".
     */
    @Test
    public void testDecodeEnabled() {
        final var device = Json.decodeValue("{\"enabled\": true}", Device.class);
        assertThat(device).isNotNull();
        assertThat(device.isEnabled());
    }

    /**
     * Decode "ext" section.
     */
    @Test
    public void testDecodeExt() {
        final var device = Json.decodeValue("{\"ext\": {\"foo\": \"bar\"}}", Device.class);
        assertThat(device).isNotNull();
        assertThat(device.isEnabled());

        final var ext = device.getExtensions();
        assertThat(ext).isNotNull();
        assertThat(ext.get("foo")).isEqualTo("bar");
    }

    /**
     * Encode with absent "enabled" flag.
     */
    @Test
    public void testEncodeDefault() {
        final var json = JsonObject.mapFrom(new Device());
        assertThat(json).isNotNull();
        assertThat(json.getBoolean("enabled")).isNull();
        assertThat(json.getJsonObject("ext")).isNull();
        assertThat(json).isEmpty();
    }

    /**
     * Encode device with "enabled=true".
     */
    @Test
    public void testEncodeEnabled() {
        final var device = new Device();
        device.setEnabled(true);
        final var json = JsonObject.mapFrom(device);
        assertThat(json).isNotNull();
        assertThat(json.getBoolean("enabled")).isTrue();
        assertThat(json.getJsonObject("ext")).isNull();
    }

    /**
     * Encode device with "enabled=false".
     */
    @Test
    public void testEncodeDisabled() {
        final var device = new Device();
        device.setEnabled(false);
        final var json = JsonObject.mapFrom(device);
        assertThat(json).isNotNull();
        assertThat(json.getBoolean("enabled")).isFalse();
        assertThat(json.getJsonObject("ext")).isNull();
    }

    /**
     * Check whether 'via' cannot be set while 'memberOf' is set.
     */
    @Test
    public void testSettingMemberOfAndVia() {
        final var device = new Device();
        final ArrayList<String> list = new ArrayList<>();
        list.add("a");
        device.setMemberOf(list);
        Assertions.assertThrows(IllegalArgumentException.class, () -> device.setVia(list),
                "Property 'memberOf' and 'via' must not be set at the same time");
    }

    /**
     * Check whether 'viaGroups' cannot be set while 'memberOf' is set.
     */
    @Test
    public void testSettingMemberOfAndViaGroups() {
        final var device = new Device();
        final ArrayList<String> list = new ArrayList<>();
        list.add("a");
        device.setMemberOf(list);
        Assertions.assertThrows(IllegalArgumentException.class, () -> device.setViaGroups(list),
                "Property 'memberOf' and 'viaGroups' must not be set at the same time");
    }

    /**
     * Check whether 'memberOf' cannot be set while 'via' is set.
     */
    @Test
    public void testSettingViaAndMemberOf() {
        final var device = new Device();
        final ArrayList<String> list = new ArrayList<>();
        list.add("a");
        device.setVia(list);
        Assertions.assertThrows(IllegalArgumentException.class, () -> device.setMemberOf(list),
                "Property 'via' and 'memberOf' must not be set at the same time");
    }

    /**
     * Check whether 'memberOf' cannot be set while 'viaGroups' is set.
     */
    @Test
    public void testSettingViaGroupsAndMemberOf() {
        final var device = new Device();
        final ArrayList<String> list = new ArrayList<>();
        list.add("a");
        device.setViaGroups(list);
        Assertions.assertThrows(IllegalArgumentException.class, () -> device.setMemberOf(list),
                "Property 'viaGroups' and 'memberOf' must not be set at the same time");
    }

    /**
     * Encode device with "mapper=test".
     */
    @Test
    public void testEncodeMapper() {
        final var device = new Device();
        device.setMapper("test");
        final var json = JsonObject.mapFrom(device);
        assertThat(json).isNotNull();
        assertThat(json.getString("mapper")).isEqualTo("test");
    }
}
