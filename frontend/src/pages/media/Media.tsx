import React, { useState, useRef, ChangeEvent } from 'react';
import ReactPlayer from 'react-player';
import { useSelector } from 'react-redux';

const Media = () => {
  const baseUrl: string = 'http://localhost:8080/api/v1/';
  const mediaUrl: string = 'media/';
  const audioList: string[] = [
    'c7192772-0c1c-11ed-861d-0242ac120003',
    'c7192772-0c1c-11ed-861d-0242ac120004',
    'c7192772-0c1c-11ed-861d-0242ac120005',
    'c7192772-0c1c-11ed-861d-0242ac120006',
  ];

  const videoRef = useRef(null);
  const { userToken } = useSelector((state) => state?.auth);

  React.useEffect(() => {
    if (videoRef.current) console.log(videoRef);
  }, [videoRef]);

  const handleEnded = (e: React.ChangeEvent<HTMLVideoElement>) => {
    console.log(e);
    if (videoRef.current) {
      console.log('ended');
    }
  };

  const getBtn = async (e) => {
    const res = await fetch(baseUrl + 'media', {
      method: 'GET',
      headers: {},
    })
      .then((res) => {
        if (res.ok) {
          return res.json();
        }
        return Promise.reject(res);
      })
      .then((data) => {
        return data;
      });
  };

  const getInfo = async (e) => {
    console.log(videoRef.current);
  };

  const renderVideo = () => {
    const fid = 'c7192772-0c1c-11ed-861d-0242ac120001';
    if (userToken) {
      const media = baseUrl + mediaUrl + `${fid}?token=${userToken}`;
      return (
        <div>
          <video
            src={baseUrl + mediaUrl + `${fid}?token=${userToken}`}
            width='720px'
            height='480px'
            controls={true}
            preload='none'
            onEnded={handleEnded}
          ></video>
          <ReactPlayer src={media} controls />
        </div>
      );
    }
    return;
  };

  const renderAudio = () => {
    if (userToken) {
      return (
        <div>
          <audio controls={true} autoPlay={true} preload='metadata' playsInline={true}>
            {audioList.map((id) => (
              <source
                key={id}
                ref={videoRef}
                src={baseUrl + mediaUrl + `${id}?token=${userToken}`}
                type='audio/mp3'
              />
            ))}
          </audio>
        </div>
      );
    }
    return;
  };

  return (
    <>
      <div className=''>
        <ReactPlayer src='https://www.youtube.com/watch?v=LXb3EKWsInQ' controls />
      </div>
      {renderVideo()}
      {renderAudio()}
      {userToken && <button onClick={getBtn}>Get</button>}
      {userToken && <button onClick={getInfo}>Info</button>}
    </>
  );
};

export default Media;
