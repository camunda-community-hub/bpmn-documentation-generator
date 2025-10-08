<#import "../bpmn-template-util.ftl" as util/>
<#macro listGateways processElements>

<#assign gatewayTypes = [
{ "complexGateway": "Complex Gateways" },
{ "inclusiveGateway": "Inclusive Gateways" },
{ "exclusiveGateway": "Send Gateways" },
{ "eventBasedGateway": "Event Based Gateways" },
{ "parallelGateway": "Parallel Gateways" }
]/>

<#assign noGateways = processElements?filter(element -> element.flowType?ends_with("Gateway"))/>

#### Gateways

<#if (noGateways?size > 0)>
    <#list gatewayTypes as type>
        <#list type as gatewayType, title>
            <#assign gateways = processElements?filter(element -> element.flowType == gatewayType)/>
            <#if (gateways?size > 0)>
##### ${title}
                <#list gateways as gateway>
* <@util.emptyOrNull gateway.name "gateway" /> (${gateway.id})
                </#list>
            </#if>
        </#list>
    </#list>
<#else>
    <@util.emptySection skip=skipEmptySections section="gateways" quote=false markdown=true/>
</#if>

</#macro>