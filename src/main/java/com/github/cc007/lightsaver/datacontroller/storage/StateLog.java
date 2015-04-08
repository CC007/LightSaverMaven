/**********************************************************************
Copyright (c) 2011 Andy Jefferson and others. All rights reserved.
Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.

Contributors:
    ...
**********************************************************************/
package com.github.cc007.lightsaver.datacontroller.storage;

import java.util.HashSet;
import java.util.Set;

import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.PrimaryKey;

/**
 * Definition of an StateLog of logs.
 */
@PersistenceCapable
public class StateLog
{
    @PrimaryKey
    protected String name=null;

    protected Set<Entry> logs = new HashSet<>();

    public StateLog(String name)
    {
        this.name = name;
    }

    public String getName()
    {
        return name;
    }

    public Set<Entry> getEntries()
    {
        return logs;
    }

    @Override
    public String toString()
    {
        return "StateLog : " + name;
    }
}