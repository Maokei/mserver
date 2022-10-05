import React from "react";
import ReactDOM from "react-dom";
import { ModalProps } from "../../types";
import styles from "./modal.module.scss";

const Modal = ({
    isShowing,
    hide,
    modalName,
    labelText,
    isAudio,
    isVideo,
    fileType,
}: ModalProps) =>
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

                          <h4 className="title is-4">{modalName}</h4>

                          {modalName.includes("Edit media") && (
                              <div className={styles.albumArtWrapper}>
                                  <img
                                      src="https://bulma.io/images/placeholders/128x128.png"
                                      alt="placeholder"
                                  />
                                  <div className={styles.iconGroup}>
                                      <span className="icon">
                                          <i className="fas fa-trash"></i>
                                      </span>
                                      <span className="icon">
                                          <i className="fas fa-upload"></i>
                                      </span>
                                  </div>
                              </div>
                          )}

                          {labelText.map((label, index) => (
                              <div
                                  key={index}
                                  className={`field ${styles.inputField}`}
                              >
                                  <label className="label">{label}</label>
                                  <div className="control">
                                      <input
                                          className="input"
                                          type="text"
                                          placeholder={label}
                                      />
                                  </div>
                              </div>
                          ))}

                          {isAudio || isVideo ? (
                              <label
                                  className={`checkbox ${styles.filetypeLabel}`}
                              >
                                  <input type="checkbox" />
                                  <p className={styles.fileType}>{fileType}</p>
                              </label>
                          ) : null}

                          <div className={`field is-grouped`}>
                              <p className="control">
                                  <a href="/" className="button is-light">
                                      Cancel
                                  </a>
                              </p>
                              <p className="control">
                                  <a
                                      href="/"
                                      className={`button ${styles.submitButton}`}
                                  >
                                      Submit
                                  </a>
                              </p>
                          </div>
                      </div>
                  </div>
              </React.Fragment>,
              document.body
          )
        : null;

export default Modal;
