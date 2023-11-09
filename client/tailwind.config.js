/** @type {import('tailwindcss').Config} */
export default {
    content: ["./index.html", "./src/**/*.{js,jsx,ts,tsx}",],
    theme: {
      extend: {
        backgroundImage: {
            'stratego': "url(/ai_generated_stratego_game4.jpg)"
        },
      },
    },
    plugins: [
      require('@tailwindcss/forms'),
      require('@tailwindcss/typography')
    ],
  }
