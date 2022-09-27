import React from "react";
import ReactDOM from "react-dom";
import styles from "./modal.module.scss";

const Modal = ({ isShowing, hide }: { isShowing: boolean; hide: () => void }) =>
    isShowing
        ? ReactDOM.createPortal(
              <React.Fragment>
                  <div className={styles.overlay} />
                  <div
                      className={styles.wrapper}
                      aria-modal
                      aria-hidden
                      tabIndex={-1}
                      role="dialog"
                  >
                      <div className={styles.modal}>
                          <div className={styles.modalHeader}>
                              <button
                                  type="button"
                                  className={styles.closeButton}
                                  data-dismiss="modal"
                                  aria-label="Close"
                                  onClick={hide}
                              >
                                  <span aria-hidden="true">&times;</span>
                              </button>
                          </div>
                          <p>Hello, I'm a modal.</p>
                      </div>
                  </div>
              </React.Fragment>,
              document.body
          )
        : null;

export default Modal;
