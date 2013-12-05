
package server.coordination;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the server.coordination package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _Commit_QNAME = new QName("http://coordination.server/", "commit");
    private final static QName _PrepareResponse_QNAME = new QName("http://coordination.server/", "prepareResponse");
    private final static QName _RollbackResponse_QNAME = new QName("http://coordination.server/", "rollbackResponse");
    private final static QName _AbortResponse_QNAME = new QName("http://coordination.server/", "abortResponse");
    private final static QName _CommitResponse_QNAME = new QName("http://coordination.server/", "commitResponse");
    private final static QName _Abort_QNAME = new QName("http://coordination.server/", "abort");
    private final static QName _Prepare_QNAME = new QName("http://coordination.server/", "prepare");
    private final static QName _Rollback_QNAME = new QName("http://coordination.server/", "rollback");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: server.coordination
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link server.coordination.AbortResponse }
     * 
     */
    public AbortResponse createAbortResponse() {
        return new AbortResponse();
    }

    /**
     * Create an instance of {@link Prepare }
     * 
     */
    public Prepare createPrepare() {
        return new Prepare();
    }

    /**
     * Create an instance of {@link server.coordination.CommitResponse }
     * 
     */
    public CommitResponse createCommitResponse() {
        return new CommitResponse();
    }

    /**
     * Create an instance of {@link Rollback }
     * 
     */
    public Rollback createRollback() {
        return new Rollback();
    }

    /**
     * Create an instance of {@link RollbackResponse }
     * 
     */
    public RollbackResponse createRollbackResponse() {
        return new RollbackResponse();
    }

    /**
     * Create an instance of {@link server.coordination.Abort }
     * 
     */
    public Abort createAbort() {
        return new Abort();
    }

    /**
     * Create an instance of {@link PrepareResponse }
     * 
     */
    public PrepareResponse createPrepareResponse() {
        return new PrepareResponse();
    }

    /**
     * Create an instance of {@link server.coordination.Commit }
     * 
     */
    public Commit createCommit() {
        return new Commit();
    }

    /**
     * Create an instance of {@link javax.xml.bind.JAXBElement }{@code <}{@link server.coordination.Commit }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://coordination.server/", name = "commit")
    public JAXBElement<Commit> createCommit(Commit value) {
        return new JAXBElement<Commit>(_Commit_QNAME, Commit.class, null, value);
    }

    /**
     * Create an instance of {@link javax.xml.bind.JAXBElement }{@code <}{@link PrepareResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://coordination.server/", name = "prepareResponse")
    public JAXBElement<PrepareResponse> createPrepareResponse(PrepareResponse value) {
        return new JAXBElement<PrepareResponse>(_PrepareResponse_QNAME, PrepareResponse.class, null, value);
    }

    /**
     * Create an instance of {@link javax.xml.bind.JAXBElement }{@code <}{@link RollbackResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://coordination.server/", name = "rollbackResponse")
    public JAXBElement<RollbackResponse> createRollbackResponse(RollbackResponse value) {
        return new JAXBElement<RollbackResponse>(_RollbackResponse_QNAME, RollbackResponse.class, null, value);
    }

    /**
     * Create an instance of {@link javax.xml.bind.JAXBElement }{@code <}{@link server.coordination.AbortResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://coordination.server/", name = "abortResponse")
    public JAXBElement<AbortResponse> createAbortResponse(AbortResponse value) {
        return new JAXBElement<AbortResponse>(_AbortResponse_QNAME, AbortResponse.class, null, value);
    }

    /**
     * Create an instance of {@link javax.xml.bind.JAXBElement }{@code <}{@link server.coordination.CommitResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://coordination.server/", name = "commitResponse")
    public JAXBElement<CommitResponse> createCommitResponse(CommitResponse value) {
        return new JAXBElement<CommitResponse>(_CommitResponse_QNAME, CommitResponse.class, null, value);
    }

    /**
     * Create an instance of {@link javax.xml.bind.JAXBElement }{@code <}{@link server.coordination.Abort }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://coordination.server/", name = "abort")
    public JAXBElement<Abort> createAbort(Abort value) {
        return new JAXBElement<Abort>(_Abort_QNAME, Abort.class, null, value);
    }

    /**
     * Create an instance of {@link javax.xml.bind.JAXBElement }{@code <}{@link Prepare }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://coordination.server/", name = "prepare")
    public JAXBElement<Prepare> createPrepare(Prepare value) {
        return new JAXBElement<Prepare>(_Prepare_QNAME, Prepare.class, null, value);
    }

    /**
     * Create an instance of {@link javax.xml.bind.JAXBElement }{@code <}{@link Rollback }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://coordination.server/", name = "rollback")
    public JAXBElement<Rollback> createRollback(Rollback value) {
        return new JAXBElement<Rollback>(_Rollback_QNAME, Rollback.class, null, value);
    }

}
