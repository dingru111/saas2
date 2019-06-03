package com.gta.train.platform.saas.util.io;

import java.io.*;
import java.util.Hashtable;
import java.util.Locale;

/**
 * Created by yi.wang1 on 2015/6/15.
 */
public class MimeUtility {

    private static String defaultJavaCharset;
    private static Hashtable mime2java = new Hashtable(10);
    private static Hashtable java2mime = new Hashtable(40);
    private static String defaultMIMECharset;
    private static final boolean foldEncodedWords = false;

    public static String encodeText(String text) throws UnsupportedEncodingException {
        return encodeText(text, (String) null, (String) null);
    }

    public static String encodeText(String text, String charset, String encoding) throws UnsupportedEncodingException {
        return encodeWord(text, charset, encoding, false);
    }

    private static String encodeWord(String string, String charset, String encoding, boolean encodingWord) throws UnsupportedEncodingException {
        int ascii = checkAscii(string);
        if (ascii == 1) {
            return string;
        } else {
            String jcharset;
            if (charset == null) {
                jcharset = getDefaultJavaCharset();
                charset = getDefaultMIMECharset();
            } else {
                jcharset = javaCharset(charset);
            }

            if (encoding == null) {
                if (ascii != 3) {
                    encoding = "Q";
                } else {
                    encoding = "B";
                }
            }

            boolean b64;
            if (encoding.equalsIgnoreCase("B")) {
                b64 = true;
            } else {
                if (!encoding.equalsIgnoreCase("Q")) {
                    throw new UnsupportedEncodingException("Unknown transfer encoding: " + encoding);
                }

                b64 = false;
            }

            StringBuffer outb = new StringBuffer();
            doEncode(string, b64, jcharset, 68 - charset.length(), "=?" + charset + "?" + encoding + "?", true, encodingWord, outb);
            return outb.toString();
        }
    }

    public static String getDefaultJavaCharset() {
        if (defaultJavaCharset == null) {
            String mimecs = null;

            try {
                mimecs = System.getProperty("mail.mime.charset");
            } catch (SecurityException var3) {
                ;
            }

            if (mimecs != null && mimecs.length() > 0) {
                defaultJavaCharset = javaCharset(mimecs);
                return defaultJavaCharset;
            }

            try {
                defaultJavaCharset = System.getProperty("file.encoding", "8859_1");
            } catch (SecurityException var4) {
                class NullInputStream extends InputStream {
                    NullInputStream() {
                    }

                    @Override
					public int read() {
                        return 0;
                    }
                }

                InputStreamReader reader = new InputStreamReader(new NullInputStream());
                defaultJavaCharset = reader.getEncoding();
                if (defaultJavaCharset == null) {
                    defaultJavaCharset = "8859_1";
                }
            }
        }

        return defaultJavaCharset;
    }

    static int checkAscii(String s) {
        int ascii = 0;
        int non_ascii = 0;
        int l = s.length();

        for (int i = 0; i < l; ++i) {
            if (nonascii(s.charAt(i))) {
                ++non_ascii;
            } else {
                ++ascii;
            }
        }

        if (non_ascii == 0) {
            return 1;
        } else if (ascii > non_ascii) {
            return 2;
        } else {
            return 3;
        }
    }

    static final boolean nonascii(int b) {
        return b >= 127 || b < 32 && b != 13 && b != 10 && b != 9;
    }

    public static String javaCharset(String charset) {
        if (mime2java != null && charset != null) {
            String alias = (String) mime2java.get(charset.toLowerCase(Locale.ENGLISH));
            return alias == null ? charset : alias;
        } else {
            return charset;
        }
    }

    static String getDefaultMIMECharset() {
        if (defaultMIMECharset == null) {
            try {
                defaultMIMECharset = System.getProperty("mail.mime.charset");
            } catch (SecurityException var1) {
                ;
            }
        }

        if (defaultMIMECharset == null) {
            defaultMIMECharset = mimeCharset(getDefaultJavaCharset());
        }

        return defaultMIMECharset;
    }

    public static String mimeCharset(String charset) {
        if (java2mime != null && charset != null) {
            String alias = (String) java2mime.get(charset.toLowerCase(Locale.ENGLISH));
            return alias == null ? charset : alias;
        } else {
            return charset;
        }
    }

    private static void doEncode(String string, boolean b64, String jcharset, int avail, String prefix, boolean first, boolean encodingWord, StringBuffer buf) throws UnsupportedEncodingException {
        byte[] bytes = string.getBytes(jcharset);
        int len;
        if (b64) {
            len = BEncoderStream.encodedLength(bytes);
        } else {
            len = QEncoderStream.encodedLength(bytes, encodingWord);
        }

        int size;
        if (len > avail && (size = string.length()) > 1) {
            doEncode(string.substring(0, size / 2), b64, jcharset, avail, prefix, first, encodingWord, buf);
            doEncode(string.substring(size / 2, size), b64, jcharset, avail, prefix, false, encodingWord, buf);
        } else {
            ByteArrayOutputStream os = new ByteArrayOutputStream();
            Object eos;
            if (b64) {
                eos = new BEncoderStream(os);
            } else {
                eos = new QEncoderStream(os, encodingWord);
            }

            try {
                ((OutputStream) eos).write(bytes);
                ((OutputStream) eos).close();
            } catch (IOException var15) {
                ;
            }

            byte[] encodedBytes = os.toByteArray();
            if (!first) {
                if (foldEncodedWords) {
                    buf.append("\r\n ");
                } else {
                    buf.append(" ");
                }
            }

            buf.append(prefix);

            for (int i = 0; i < encodedBytes.length; ++i) {
                buf.append((char) encodedBytes[i]);
            }

            buf.append("?=");
        }

    }
}
