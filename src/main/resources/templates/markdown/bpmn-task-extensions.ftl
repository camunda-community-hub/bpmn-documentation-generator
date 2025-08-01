<#import "bpmn-task-extensions-definitions.ftl" as definition/>
<#import "bpmn-task-extensions-headers.ftl" as header/>
<#import "bpmn-task-extensions-inputs.ftl" as input/>
<#import "bpmn-task-extensions-outputs.ftl" as output/>
<#import "bpmn-task-extensions-properties.ftl" as properties/>
<#import "bpmn-task-extensions-execution-listeners.ftl" as executionListeners/>
<#import "bpmn-task-extensions-example-data.ftl" as exampleData/>


<#macro listExtensions extensions>
    <#if extensions.definitions?has_content>
        <@definition.listDefinition extensions.definition />
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