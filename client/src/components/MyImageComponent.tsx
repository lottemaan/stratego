import React from 'react';

const MyImageComponent: React.FC = () => {
  return (
    <div className="min-h-screen flex items-center justify-center bg-stratego">
      <div>
        <img
          src="../../public/stratego.jpg" // Replace with the actual path to your image
          alt="Your Image"
          className="w-64 h-64 object-contain"
        />
      </div>
    </div>
  );
};

export default MyImageComponent;