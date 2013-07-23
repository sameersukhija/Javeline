package com.lumata.common.testing.system;

import org.apache.commons.codec.binary.Base64;

import java.io.UnsupportedEncodingException;

/**
 * Created with IntelliJ IDEA.
 * User: adipasquale
 * Date: 7/22/13
 * Time: 5:46 PM
 * To change this template use File | Settings | File Templates.
 */
public class Security {

    public static String encrypt( String text ) {

        String  encrypted = new String();

        try {

            String codified = Security.coding( text );

            encrypted = new String( Base64.encodeBase64( codified.getBytes() ), "UTF-8");

        } catch ( UnsupportedEncodingException e ) {}

        return encrypted;

    }

    public static String decrypt( String text ) {

        String decrypted = new String();

        try {

            decrypted = Security.coding( new String(  Base64.decodeBase64( text.getBytes() ), "UTF-8" ) );

        } catch ( UnsupportedEncodingException e ) {}

        return decrypted;

    }

    private static String coding( String text ) {

        String codified = new String();

        try {

            byte[] text_in_bytes = text.getBytes("US-ASCII");

            for( int i = 0; i < text_in_bytes.length; i++ ) {

                StringBuilder bytes = new StringBuilder( Integer.toBinaryString( text_in_bytes[ i ] ) );

                for( int j = 0; j < bytes.length() - 1; j += 2 ) {

                    char tmp = bytes.charAt( j + 1 );
                    bytes.setCharAt( j + 1, bytes.charAt( j ) );
                    bytes.setCharAt( j , tmp );

                }

                if( bytes.charAt( bytes.length() - 1 ) == '0' ) bytes.setCharAt( bytes.length() - 1 , '1' );
                else bytes.setCharAt(bytes.length() - 1, '0');

                int charCode = Integer.parseInt( bytes.toString(), 2 );
                codified = codified + new Character((char)charCode).toString();

            }

        } catch ( UnsupportedEncodingException e ) {}

        return codified;

    }

}
