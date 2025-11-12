<#macro listHeader headers>

    <#if headers?has_content>
###### Headers
| <#list headers[0] as headerKey, headerValue><#list headerValue as key, value>${key?capitalize} |</#list></#list>
| <#list headers[0] as headerKey, headerValue><#list headerValue as key, value>--- | </#list></#list>
| <#list headers as header><#list header as headerKey, headerValue><#list headerValue as key, value>${value} | </#list></#list></#list>
    <#else>
<@util.emptySection skip=skipEmptySections section="headers defined" quote=false />
    </#if>

</#macro>