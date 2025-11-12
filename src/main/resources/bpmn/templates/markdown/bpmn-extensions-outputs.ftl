<#macro listOutput outputs>

    <#if outputs?has_content>
###### Outputs
| <#list outputs[0] as outputKey, outputValue><#list outputValue as key, value>${key?capitalize} |</#list></#list>
| <#list outputs[0] as outputKey, outputValue><#list outputValue as key, value>--- | </#list></#list>
        <#list outputs as output>
            <#list output as outputKey, outputValue>
| <#list outputValue as key, value>${value} | </#list>
            </#list>
        </#list>
    <#else>
<@util.emptySection skip=skipEmptySections section="outputs defined" quote=false />
    </#if>

</#macro>