<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0"
    xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
    xmlns:S="http://schemas.xmlsoap.org/soap/envelope/"
    xmlns:ns2="http://bank.main/"
    xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
    xmlns:xs="http://www.w3.org/2001/XMLSchema">

    <xsl:output method="text" encoding="UTF-8"/>

    <xsl:template match="/">
        @prefix ex: &lt;http://example.org/vocab#&gt; .
        @prefix xsd: &lt;http://www.w3.org/2001/XMLSchema#&gt; .

        &lt;http://example.org/transaction/001&gt; a ex:TransactionResult ;
            ex:data     "<xsl:value-of select="//return/data"/>"^^xsd:double ;
            ex:message  "<xsl:value-of select="//return/message"/>"@en ;
            ex:status   "<xsl:value-of select="//return/status"/>"^^xsd:integer .
    </xsl:template>
</xsl:stylesheet>