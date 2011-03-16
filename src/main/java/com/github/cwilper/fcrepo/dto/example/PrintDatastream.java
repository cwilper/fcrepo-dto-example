package com.github.cwilper.fcrepo.dto.example;

import com.github.cwilper.fcrepo.dto.core.Datastream;
import com.github.cwilper.fcrepo.dto.core.DatastreamVersion;
import com.github.cwilper.fcrepo.dto.core.FedoraObject;
import com.github.cwilper.fcrepo.dto.foxml.FOXMLReader;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

/**
 * For a given FOXML file, prints the content of the latest version of a
 * given datastream to standard output.
 *
 * Usage: PrintDatastream foxml-filename.xml DATASTREAM_ID
 */
public class PrintDatastream {

    public static void main(String[] args) throws Exception {

        if (args.length != 2) {
            System.out.println("ERROR: Two arguments (FOXML filename, "
                    + "datastream id) required.");
            System.exit(1);
        }

        InputStream in = new FileInputStream(new File(args[0]));
        FOXMLReader r = new FOXMLReader();
        try {
            FedoraObject o = r.readObject(in);
            Datastream d = o.datastreams().get(args[1]);
            DatastreamVersion v = d.versions().first();
            System.out.println(v.inlineXML().value());
        } finally {
            r.close();
        }
    }
}
