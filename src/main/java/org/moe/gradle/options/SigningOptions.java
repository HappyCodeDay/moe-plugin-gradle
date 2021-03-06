/*
Copyright (C) 2016 Migeran

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
*/

package org.moe.gradle.options;

import org.moe.gradle.MoeSDK;
import org.moe.gradle.anns.IgnoreUnused;
import org.moe.gradle.anns.Nullable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class SigningOptions {
    private static final Logger LOG = LoggerFactory.getLogger(SigningOptions.class);

    @Nullable
    private String defaultDevelopmentTeam;

    public SigningOptions() {
        final Properties props = new Properties();
        try {
            props.load(new FileInputStream(MoeSDK.USER_MOE_HOME.resolve("default.properties").toFile()));
            defaultDevelopmentTeam = props.getProperty("developmentTeam");
        } catch (FileNotFoundException ignore) {
            // This is ok, don't fail, don't log
        } catch (IOException e) {
            // Some other error occurred, print but don't fail
            LOG.warn("Failed to read 'default.properties' file", e);
        }
    }

    @Nullable
    private String provisioningProfile;

    @Nullable
    public String getProvisioningProfile() {
        return provisioningProfile;
    }

    @IgnoreUnused
    public void setProvisioningProfile(@Nullable String provisioningProfile) {
        this.provisioningProfile = provisioningProfile;
    }

    @Nullable
    private String signingIdentity;

    @Nullable
    public String getSigningIdentity() {
        return signingIdentity;
    }

    @IgnoreUnused
    public void setSigningIdentity(@Nullable String signingIdentity) {
        this.signingIdentity = signingIdentity;
    }

    @Nullable
    private String developmentTeam;

    @Nullable
    public String getDevelopmentTeam() {
        return developmentTeam == null ? defaultDevelopmentTeam : developmentTeam;
    }

    public boolean usesDefaultDevelopmentTeam() {
        return developmentTeam == null;
    }

    @IgnoreUnused
    public void setDevelopmentTeam(String developmentTeam) {
        this.developmentTeam = developmentTeam;
    }
}
