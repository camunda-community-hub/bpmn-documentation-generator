<#import "bpmn-template-util.ftl" as util/>

<#macro listTasks processElements type>

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

<#if type == "markdown">
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
No tasks.
    </#if>

<#else>
    <details>
        <summary><h4>Tasks</h4></summary>
        <#if (noTasks?size > 0)>
            <#list taskTypes as type>
                <#list type as taskType, title>
                    <#assign tasks = processElements?filter(element -> element.flowType == taskType)/>
                    <#if (tasks?size > 0)>
                        <h5>${title}</h5>
                        <ul>
                            <#list tasks as task>
                                <li><@util.emptyOrNull task.name "task" /> (${task.id})</li>
                            </#list>
                        </ul>
                    </#if>
                </#list>
            </#list>
        <#else>
            <p>No tasks.</p>
        </#if>
    </details>
</#if>
</#macro>