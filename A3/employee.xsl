<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    <xsl:template match="/">
        <html>
        <head>
            <title>Employee Data</title>
        </head>
        <body>
            <table border="1" width="100%">
                <tr style="background-color:teal; color:white; font-size:20px">
                    <th>Name</th>
                    <th>Age</th>
                    <th>Salary</th>
                    <th>Address</th>
                </tr>
                <xsl:for-each select="employees/info">
                    <tr>
                        <td><xsl:value-of select="name"/></td>
                        <td><xsl:value-of select="age"/></td>
                        <td><xsl:value-of select="salary"/></td>
                        <td><xsl:value-of select="address"/></td>
                    </tr>
                </xsl:for-each>
            </table>
        </body>
        </html>
    </xsl:template>
</xsl:stylesheet>