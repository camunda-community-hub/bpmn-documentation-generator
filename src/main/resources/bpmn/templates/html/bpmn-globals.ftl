<#import "../bpmn-template-util.ftl" as util/>

<#macro listGlobals bpmn>

    <details ${openSections}>
        <summary><h2>Globals</h2></summary>
        <h3>Messages</h3>
        <#if bpmn.messages?has_content>
            <#list bpmn.messages>
                <ul>
                    <#items as message>
                        <li><@util.emptyOrNull message.name "message" /> <sup id="${message.id}">(${message.id})</sup></li>
                    </#items>
                </ul>
            </#list>
        <#else>
            <@util.emptySection skip=skipEmptySections section="global messages" quote=false />
        </#if>

        <h3>Errors</h3>
        <#if bpmn.errors?has_content>
            <#list bpmn.errors>
                <ul>
                    <#items as error>
                        <li><@util.emptyOrNull error.name "error" /> <sup id="${error.id}">(${error.id})</sup> - <@util.emptyOrNull error.errorCode "error code" /></li>
                    </#items>
                </ul>
            </#list>
        <#else>
            <@util.emptySection skip=skipEmptySections section="global errors" quote=false />
        </#if>

        <h3>Signals</h3>
        <#if bpmn.signals?has_content>
            <#list bpmn.signals>
                <ul>
                    <#items as signal>
                        <li><@util.emptyOrNull signal.name "signal" /> <sup id="${signal.id}">(${signal.id})</sup></li>
                    </#items>
                </ul>
            </#list>
        <#else>
            <@util.emptySection skip=skipEmptySections section="global signals" quote=false />
        </#if>

        <h3>Escalations</h3>
        <#if bpmn.escalations?has_content>
            <#list bpmn.escalations>
                <ul>
                    <#items as escalation>
                        <li><@util.emptyOrNull escalation.name "escalation" /> <sup id="${escalation.id}">(${escalation.id})</sup> - <@util.emptyOrNull escalation.escalationCode "escalation code" /></li>
                    </#items>
                </ul>
            </#list>
        <#else>
            <@util.emptySection skip=skipEmptySections section="global escalations" quote=false />
        </#if>

    </details> <!-- End of Globals -->
</#macro>