<#import "bpmn-extensions-task-definitions.ftl" as definition/>
<#import "bpmn-extensions-headers.ftl" as header/>
<#import "bpmn-extensions-inputs.ftl" as input/>
<#import "bpmn-extensions-outputs.ftl" as output/>
<#import "bpmn-extensions-properties.ftl" as properties/>
<#import "bpmn-extensions-execution-listeners.ftl" as executionListeners/>
<#import "bpmn-extensions-example-data.ftl" as exampleData/>


<#macro listExtensions extensions>
    <#if extensions.definitions?has_content>
        <@definition.listDefinition extensions.taskDefinition />
    </#if>
    <#if extensions.header?has_content>
        <@header.listHeader extensions.header />
    </#if>
    <#if extensions.input?has_content>
        <@input.listInput extensions.input />
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
</#macro>