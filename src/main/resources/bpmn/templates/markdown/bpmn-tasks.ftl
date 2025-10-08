<#import "../bpmn-template-util.ftl" as util/>
<#import "bpmn-extensions.ftl" as extensions>

<#macro listTasks processElements>

<#assign taskTypes = [
{ "serviceTask": "Service Tasks" },
{ "userTask": "User Tasks" },
{ "sendTask": "Send Tasks" },
{ "receiveTask": "Receive Tasks" },
{ "businessRuleTask": "Business Rule Tasks" },
{ "scriptTask": "Script Tasks" },
{ "anonymousTask": "Anonymous Tasks" }
]/>

<#assign noTasks = processElements?filter(element -> element.flowType?ends_with("Task"))/>

#### Tasks

<#if (noTasks?size > 0)>
    <#list taskTypes as type>
        <#list type as taskType, title>
            <#assign tasks = processElements?filter(element -> element.flowType == taskType)/>
            <#if (tasks?size > 0)>
##### ${title}

                <#list tasks as task>
* <@util.emptyOrNull task.name "task" /> (${task.id})
                </#list>
            </#if>
        </#list>
    </#list>
<#else>
    <@util.emptySection skip=skipEmptySections section="tasks" quote=false markdown=true/>
</#if>
<#if (noTasks?size > 0)>
    <#list taskTypes as type>
        <#list type as taskType, title>
            <#assign tasks = processElements?filter(element -> element.flowType == taskType)/>
            <#if (tasks?size > 0)>
##### ${title}

                <#list tasks as task>
<@util.emptyOrNull task.name "task" />
|Property|Value|Version|
|---|---|---|
|ID|${task.id}|n/a|
|Template|<@util.emptyOrNull task.template.name "template" />|<@util.emptyOrNull task.template.version "template version" />|
                    <#if task.documentation?has_content>
> ${task.documentation}
                    <#else>
                        <@util.emptySection skip=skipEmptySections section="documentation" quote=false markdown=true/>
                    </#if>
                    <#if task.extensions?has_content>
                        <@extensions.listExtensions task.extensions />
                    </#if>
                </#list>
            </#if>
        </#list>
    </#list>
</#if>

</#macro>