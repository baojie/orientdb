/*
 * Copyright 1999-2010 Luca Garulli (l.garulli--at--orientechnologies.com)
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
package com.orientechnologies.orient.core.index;

import java.util.Set;

import com.orientechnologies.orient.core.db.record.OIdentifiable;
import com.orientechnologies.orient.core.db.record.ORecordLazySet;

/**
 * Abstract unique index class.
 * 
 * @author Luca Garulli
 * 
 */
public class OIndexDictionary extends OIndexMVRBTreeAbstract {
	public OIndexDictionary() {
		super("DICTIONARY");
	}

	public OIndex put(final Object iKey, final OIdentifiable iSingleValue) {
		acquireExclusiveLock();

		try {
			Set<OIdentifiable> values = map.get(iKey);

			if (values == null)
				values = new ORecordLazySet(configuration.getDatabase());
			else
				values.clear();

			values.add(iSingleValue);

			map.put(iKey, values);
			return this;

		} finally {
			releaseExclusiveLock();
		}
	}
}