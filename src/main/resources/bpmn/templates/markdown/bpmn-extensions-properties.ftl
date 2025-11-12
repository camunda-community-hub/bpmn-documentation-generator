<#macro listProperties properties>

    <#if properties?has_content>
###### Properties

|
        <#list properties[0] as propertyKey, propertyValue>
            <#list propertyValue as key, value>
 ${key?capitalize} |
            </#list>
        </#list>
|---|---|
|
        <#list properties as property>
            <#list property as propertyKey, propertyValue>
                <#list propertyValue as key, value>
 ${value} |
                </#list>
            </#list>
        </#list>
    <#else>
<@util.emptySection skip=skipEmptySections section="properties defined" quote=false />
    </#if>

</#macro>