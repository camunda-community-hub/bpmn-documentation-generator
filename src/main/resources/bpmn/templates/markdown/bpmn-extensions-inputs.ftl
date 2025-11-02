<#macro listInput inputs>

    <#if inputs?has_content>
###### Inputs

|
        <#list inputs[0] as inputKey, inputValue>
            <#list inputValue as key, value>
 ${key?capitalize} |
            </#list>
        </#list>
|-|-|-|
        <#list inputs as input>
            <#list input as inputKey, inputValue>
|
                <#list inputValue as key, value>
 ${value} |
                </#list>
            </#list>
        </#list>
    <#else>
<@util.emptySection skip=skipEmptySections section="inputs defined" quote=false />
    </#if>

</#macro>