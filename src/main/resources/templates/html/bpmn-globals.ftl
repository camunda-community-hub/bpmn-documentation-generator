<#import "../bpmn-template-util.ftl" as util/>

<#macro listGlobals bpmn>

    <details>
        <summary><h2>Globals</h2></summary>
        <h3>Messages</h3>
        <#if bpmn.messages?has_content>
            <#list bpmn.messages>
                <ul>
                    <#items as message>
                        <li><@util.emptyOrNull message.name "message" /> <sup>(${message.id})</sup></li>
                    </#items>
                </ul>
            </#list>
        <#else>
            <p>No global messages.</p>
        </#if>

        <h3>Errors</h3>
        <#if bpmn.errors?has_content>
            <#list bpmn.errors>
                <ul>
                    <#items as error>
                        <li><@util.emptyOrNull error.name "error" /> <sup>(${error.id})</sup> - Code ${error.errorCode}</li>
                    </#items>
                </ul>
            </#list>
        <#else>
            <p>No global errors.</p>
        </#if>

        <h3>Signals</h3>
        <#if bpmn.signals?has_content>
            <#list bpmn.signals>
                <ul>
                    <#items as signal>
                        <li><@util.emptyOrNull signal.name "signal" /> <sup>(${signal.id})</sup></li>
                    </#items>
                </ul>
            </#list>
        <#else>
            <p>No global signals.</p>
        </#if>

        <h3>Escalations</h3>
        <#if bpmn.escalations?has_content>
            <#list bpmn.escalations>
                <ul>
                    <#items as escalation>
                        <li><@util.emptyOrNull escalation.name "escalation" /> <sup>(${escalation.id})</sup> - Code ${escalation.escalationCode}</li>
                    </#items>
                </ul>
            </#list>
        <#else>
            <p>No global escalations.</p>
        </#if>

    </details> <!-- End of Globals -->
</#macro>