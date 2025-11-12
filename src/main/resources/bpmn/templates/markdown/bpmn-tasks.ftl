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

## Tasks

<#if (noTasks?size > 0)>
    <#list taskTypes as type>
        <#list type as taskType, title>
            <#assign tasks = processElements?filter(element -> element.flowType == taskType)/>
            <#if (tasks?size > 0)>
### ${title}

                <#list tasks as task>
<#assign taskName>
<#compress>
<@util.emptyOrNull task.name?trim "task" />
</#compress>
</#assign>
* [${taskName?trim}](#${task.id})
                </#list>
            </#if>
        </#list>
    </#list>
<#else>
    <@util.emptySection skip=skipEmptySections section="tasks" quote=false />
</#if>
<#if (noTasks?size > 0)>
    <#list taskTypes as type>
        <#list type as taskType, title>
            <#assign tasks = processElements?filter(element -> element.flowType == taskType)/>
            <#if (tasks?size > 0)>
## ${title}
                <#list tasks as task>
<#assign templateName><#compress><@util.emptyOrNull task.template.name "template" /></#compress></#assign>
<#assign templateVersion><#compress><@util.emptyOrNull task.template.version "template version" /></#compress></#assign>
<a id="${task.id}"></a>
### <@util.emptyOrNull task.name "task" />
| Property | Value | Version |
| --- | --- | --- |
| ID | ${task.id} | n/a |
<#if templateName != "No template attached">
| Template | ${templateName?trim} | ${templateVersion?trim} |
</#if>

                    <#if task.documentation?has_content>
> ${task.documentation}
                    <#else>
<@util.emptySection skip=skipEmptySections section="documentation" quote=false />
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