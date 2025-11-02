<#import "bpmn-extensions-task-definition.ftl" as definition/>
<#import "bpmn-extensions-headers.ftl" as header/>
<#import "bpmn-extensions-inputs.ftl" as input/>
<#import "bpmn-extensions-outputs.ftl" as output/>
<#import "bpmn-extensions-properties.ftl" as properties/>
<#import "bpmn-extensions-execution-listeners.ftl" as executionListeners/>
<#import "bpmn-extensions-example-data.ftl" as exampleData/>


<#macro listExtensions extensions>
    <p>
    <#if extensions.taskDefinition?has_content>
        <@definition.listTaskDefinition extensions.taskDefinition />
    </#if>
    <#if extensions.headers?has_content>
        <@header.listHeader extensions.headers />
    </#if>
    <#if extensions.inputs?has_content>
        <@input.listInput extensions.inputs />
    </#if>
    <#if extensions.outputs?has_content>
        <@output.listOutput extensions.outputs />
    </#if>
    <#if extensions.executionListeners?has_content>
        <@executionListeners.listExecutionListeners extensions.executionListeners />
    </#if>
    <#if extensions.properties?has_content>
        <@properties.listProperties extensions.properties />
    </#if>
    <#if extensions.exampleData?has_content>
        <@exampleData.listExampleData extensions.exampleData />
    </#if>
    </p>
</#macro>