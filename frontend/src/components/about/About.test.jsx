import React from 'react';
import { render } from '@testing-library/react';
import About from './About';

describe('About component', () => {
    it('should renders without crashing', () => {
        render(<About />);
    });
});
