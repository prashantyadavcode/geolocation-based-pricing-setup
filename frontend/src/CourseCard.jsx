import React from 'react';

const CourseCard = ({ course }) => {
  return (
    <div className="course-card">
      <h3 className="course-title">{course.title}</h3>
      <div className="course-meta">
        <div>
          <span className="label">Country:</span>{' '}
          <span>{course.countryCode}</span>
        </div>
        <div>
          <span className="label">Pricing Zone:</span>{' '}
          <span>{course.pricingZone}</span>
        </div>
        <div>
          <span className="label">Currency:</span>{' '}
          <span>{course.currency}</span>
        </div>
      </div>
      <div className="course-price">
        <span className="price-symbol">{course.symbol}</span>
        <span className="price-amount">{course.amount}</span>
      </div>
      <div className="course-display">
        <span className="label">Display:</span>{' '}
        <span>{course.display}</span>
      </div>
      <div className="course-source">
        <span className="label">Source:</span>{' '}
        <span>{course.source}</span>
      </div>
    </div>
  );
};

export default CourseCard;

