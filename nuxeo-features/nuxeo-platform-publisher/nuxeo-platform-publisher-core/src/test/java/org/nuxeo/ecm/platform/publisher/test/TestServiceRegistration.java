/*
 * (C) Copyright 2006-2009 Nuxeo SA (http://nuxeo.com/) and others.
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
 *
 * Contributors:
 *     Nuxeo
 */

package org.nuxeo.ecm.platform.publisher.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.nuxeo.ecm.core.api.CoreSession;
import org.nuxeo.ecm.core.test.CoreFeature;
import org.nuxeo.ecm.core.test.annotations.Granularity;
import org.nuxeo.ecm.core.test.annotations.RepositoryConfig;
import org.nuxeo.ecm.platform.publisher.api.PublisherService;
import org.nuxeo.runtime.test.runner.Deploy;
import org.nuxeo.runtime.test.runner.Features;
import org.nuxeo.runtime.test.runner.FeaturesRunner;
import org.nuxeo.runtime.test.runner.LocalDeploy;

@RunWith(FeaturesRunner.class)
@Features(CoreFeature.class)
@RepositoryConfig(cleanup = Granularity.METHOD)
@Deploy({ "org.nuxeo.ecm.platform.content.template", //
        "org.nuxeo.ecm.platform.types.api", //
        "org.nuxeo.ecm.platform.types.core", //
        "org.nuxeo.ecm.platform.versioning.api", //
        "org.nuxeo.ecm.platform.versioning", //
        "org.nuxeo.ecm.platform.query.api", //
        "org.nuxeo.ecm.platform.publisher.core.contrib", //
        "org.nuxeo.ecm.platform.publisher.core", //
})
public class TestServiceRegistration {

    @Inject
    protected CoreSession session;

    @Inject
    protected PublisherService publisherService;

    @Test
    public void testMainService() throws Exception {
        assertNotNull(publisherService);
    }

    @Test
    @LocalDeploy("org.nuxeo.ecm.platform.publisher.core:OSGI-INF/publisher-contrib.xml")
    public void testContrib() throws Exception {
        List<String> treeNames = publisherService.getAvailablePublicationTree();
        assertEquals(1, treeNames.size());
    }

}